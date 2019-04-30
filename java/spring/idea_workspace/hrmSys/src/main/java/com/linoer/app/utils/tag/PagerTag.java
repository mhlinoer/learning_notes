package com.linoer.app.utils.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 分页标签
 */
public class PagerTag extends SimpleTagSupport {

    /**
     * 定义请求URL中的占位符常量
     */
    private static final String TAG = "{0}";
    // 当前页码
    private int pageIndex;
    // 每页显示的数量
    private int pageSize;
    // 总记录数
    private int recordCount;
    // 请求url
    private String submitUrl;
    // 样式
    private String style = "sabrosus";
    // 总页数
    private int totalPage = 0;

    @Override
    public void doTag() throws JspException, IOException {
        // 定义拼接最终的结果
        StringBuilder result = new StringBuilder();
        // 定义拼接中间的页码
        StringBuilder str = new StringBuilder();

        if(recordCount > 0){
            totalPage = (this.recordCount - 1) / this.pageSize + 1;

            // 判断上一页或者下一页要不要加a标签
            if(this.pageIndex == 1) { // 首页
                str.append("<span class='disabled'>上一页</span>");
                // 计算中间的页码
                this.calcPage(str);
                // 下一些需不需要a标签
                if(this.pageIndex == totalPage){
                    str.append("<span class='disabled'>下一页</span>");
                }else {
                    String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.pageIndex + 1));
                    str.append("<a href='").append(tempUrl).append("'>上一页</a>");
                }
            }else if(this.pageIndex == totalPage){ // 尾页

            }
        }
    }

    /**
     * 计算中间页码
     * @param stringBuilder
     */
    private void calcPage(StringBuilder stringBuilder){
        // 判断总页数
        if(this.totalPage <= 11){
            // 一次性显示全部的页码
            for (int i = 0; i < this.totalPage; i++) {
                if(this.pageIndex == i){
                    addCurrentSpanTag(stringBuilder, i);
                }else {
                    addATag(stringBuilder, i);
                }
            }
        }else{
            if (this.pageIndex <= 8){
                // 靠近首页
                for (int i = 0; i <= 10; i++) {
                    if(this.pageIndex == i){
                        addCurrentSpanTag(stringBuilder, i);
                    }else {
                        addATag(stringBuilder, i);
                    }
                }
                stringBuilder.append("...");
                addATag(stringBuilder, this.totalPage);
            }else if(this.pageIndex + 8 >= this.totalPage){ // 靠近尾页
                addATag(stringBuilder, 1);
                stringBuilder.append("...");
                for (int i = this.totalPage - 10; i <= this.totalPage; i++) {
                    if(this.pageIndex == i){
                        addCurrentSpanTag(stringBuilder, i);
                    }else {
                        addATag(stringBuilder, i);
                    }
                }
            }else { // 在中间
                addATag(stringBuilder, 1);
                stringBuilder.append("...");
                for (int i = this.pageIndex - 4; i <= this.pageIndex + 4 ; i++) {
                    if(this.pageIndex == i){
                        addCurrentSpanTag(stringBuilder, i);
                    }{
                        addATag(stringBuilder, i);
                    }
                }
                stringBuilder.append("...");
                addATag(stringBuilder, this.totalPage);
            }
        }
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    private void addCurrentSpanTag(StringBuilder stringBuilder, int index){
        stringBuilder.append("<span class='current'>").append(index).append("</span>");
    }
    private void addCurrentSpanTag(StringBuilder stringBuilder, int index, String message, boolean isDisabled){
        stringBuilder.append("<span class='current'>").append(index).append("</span>");
    }
    private void addATag(StringBuilder stringBuilder, int index){
        String tmpUrl = this.submitUrl.replace(TAG, String.valueOf(index));
        stringBuilder.append("<a href='").append(tmpUrl).append("'>").append(index).append("</a>");
    }
}
