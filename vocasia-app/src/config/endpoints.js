export const endpoints = {
    'auth': {
        'login': '/auth/login',
    },
    'instructors': {
        'register': '/instructors/register',
    },
    'courses': {
        'index': '/courses',
        'store': '/courses',
        'uploadThumbnail': '/courses/:id/update-thumbnail',
        'show': '/courses/:id',
        'categories': {
            'getAll': '/categories',
        }
    },
};