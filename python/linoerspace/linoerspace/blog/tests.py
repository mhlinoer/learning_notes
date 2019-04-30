from django.test import TestCase

from blog.models import Blog
# Create your tests here.


class BlogTests(TestCase):
    def test_check_title(self):
        blog = Blog('test_case')
        self.assertIs(blog.testing(), True)
