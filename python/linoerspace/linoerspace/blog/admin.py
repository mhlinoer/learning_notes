from django.contrib import admin
from blog import models
from django.contrib import admin
# Register your models here.


class BlogAdmin(admin.ModelAdmin):
    readonly_fields = ['updated_time', 'created_time']
    list_display = ('title', 'updated_time', 'created_time')


admin.site.register(models.Blog, BlogAdmin)
