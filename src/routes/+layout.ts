import { dev } from '$app/environment';

export const prerender = true;
// Only use client-side rendering in dev mode.
// Ship no JavaScript to the user in production.
export const csr = dev;
