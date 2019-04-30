# -*- coding: utf-8 -*-
import logging

__all__ = ['GuestBackend']
logger = logging.getLogger(__package__)


class GuestBackend:

    def authenticate(self, **kwargs):
        logger.debug('login kwargs:', kwargs)
