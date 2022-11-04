import { minify } from 'html-minifier';
import { prerendering } from '$app/environment';
import type { RequestEvent, RequestHandler } from '@sveltejs/kit';

const minification_options = {
	collapseBooleanAttributes: true,
	collapseInlineTagWhitespace: true,
	collapseWhitespace: true,
	conservativeCollapse: true,
	decodeEntities: true,
	html5: true,
	ignoreCustomComments: [/^#/],
	minifyCSS: true,
	minifyJS: false,
	removeAttributeQuotes: true,
	removeComments: true,
	removeEmptyAttributes: true,
	removeEmptyElements: true,
	removeOptionalTags: true,
	removeRedundantAttributes: true,
	removeScriptTypeAttributes: true,
	removeStyleLinkTypeAttributes: true,
	sortAttributes: true,
	sortClassName: true
};

const test_no_min = true;

export async function handle({
	event,
	resolve
}: {
	event: RequestEvent;
	resolve: RequestHandler;
}): Promise<Response> {
	const response = await resolve(event);

	if (!test_no_min && prerendering && response.headers.get('content-type') === 'text/html') {
		const body = await response.text();
		const minified_html = minify(body, minification_options);
		return new Response(minified_html, response);
	}

	return response;
}