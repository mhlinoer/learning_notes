from django.db import models
from django.utils.translation import ugettext, ugettext_lazy as _

# Create your models here.


class Blog(models.Model):
    title = models.CharField(default='', max_length=128, blank=False, verbose_name=_('title'))
    updated_time = models.DateTimeField(auto_now=True, verbose_name=_('updated_time'))
    created_time = models.DateTimeField(auto_now_add=True, verbose_name=_('created_time'))

    def __unicode__(self):
        return ''.format(self.title)

    def testing(self):
        return True

    class Meta:
        db_table = 'blog'
        verbose_name = verbose_name_plural = ugettext('blog')
