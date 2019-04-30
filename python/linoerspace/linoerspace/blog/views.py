from django.shortcuts import render
from django.shortcuts import HttpResponse, get_list_or_404
from .models import Blog
from django.template import loader
import logging
logger = logging.getLogger(__package__)
# Create your views here.


def index(request):
    logger.debug("11111111111111111111")
    try:
        blogs = Blog.objects.all()
    except Exception as e:
        return HttpResponse(content='blog is not exist, extra', status=200)
    context = {
        'blogs': blogs,
    }
    template = loader.get_template('index.html')
    return HttpResponse(template.render(context, request))


def indexV2(request):
    blogs = get_list_or_404(Blog)
    return render(request, 'index_v2.html', {'blogs': blogs})


def extra(request, blog_id):
    try:
        blog = Blog.objects.get(id=blog_id)
    except Exception as e:
        return HttpResponse(content='blog is not exist, extra', status=200)
    if blog:
        return HttpResponse("extra page")
    else:
        return HttpResponse(content='blog is not exist, extra', status=200)


def vote(request, blog_id):
    try:
        blog = Blog.objects.get(id=blog_id)
    except Exception as e:
        return HttpResponse(content='blog is not exist, vote', status=200)
    if blog:
        return HttpResponse("vote page")
    else:
        return HttpResponse(content='blog is not exist, vote', status=200)


def vote_new(request, data):
    try:
        url = request.path
        # blog = Blog.objects.get(id=request.path)
        logger.debug("[blog] [vote_new] [params--- url:%s, data:%s]", url, data)
    except Exception as e:
        return HttpResponse(content='blog is not exist, vote', status=200)
    if url:
        return HttpResponse("vote_new %s" % url)
    else:
        return HttpResponse(content='blog is not exist, vote', status=200)


def detail(request, blog_id):
    if blog_id:
        try:
            blog = Blog.objects.get(id=blog_id)
        except Exception as e:
            return HttpResponse(content='blog is not exist', status=200)
        return HttpResponse(blog)
    else:
        return HttpResponse(content='blog is not exist', status=200)
