// api
export const apiBaseUrl = import.meta.env.VITE_API_BASE_URL;

// authentication storage
export const AUTH_USER = '_vocasia_user';
export const AUTH_ACCESS_TOKEN = '_vocasia_access_token';
export const AUTH_REFRESH_TOKEN = '_vocasia_refresh_token';
export const AUTH_DATE = '_vocasia_auth_date';

export const INSTRUCTOR_AUTH_DATA = '_vocasia_instructor_auth_data';

// roles
export const INSTRUCTOR = 'instructor';
export const ADMIN = 'admin';
export const STUDENT = 'student';

// AWS
export const AWS_BUCKET_NAME = import.meta.env.VITE_AWS_BUCKET_NAME;
export const AWS_REGION = import.meta.env.VITE_AWS_REGION;

// MIDTRANS
export const midtransClientKey = import.meta.env.VITE_MIDTRANS_CLIENT_KEY;
export const serviceFee = import.meta.env.VITE_SERVICE_FEE;