from django.urls import path, re_path
from . import views

app_name = 'blog'

urlpatterns = [
    path('', views.index, name='index'),
    # path('index/', views.IndexView.as_view(), name='index_v2'),
    path('<int:blog_id>/', views.detail, name='detail'),
    path('<int:blog_id>/extra/', views.extra, name='extra'),
    path('<int:blog_id>/vote/', views.vote, name='vote'),
    re_path('vote/(\d{1,2})/', views.vote_new),
]
