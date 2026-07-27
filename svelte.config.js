import { vitePreprocess } from '@sveltejs/vite-plugin-svelte';
import adapter from '@sveltejs/adapter-cloudflare';
import { inlineSvg } from '@svelte-put/inline-svg/preprocessor';
import { fileURLToPath } from 'node:url';
import path from 'node:path';

const projectRoot = fileURLToPath(new URL('.', import.meta.url));

/** @type {import('@sveltejs/kit').Config} */
const config = {
	preprocess: [
		vitePreprocess(),
		// registered directly here rather than via the @svelte-put/inline-svg vite
		// plugin's `api.sveltePreprocess`, since vite-plugin-svelte 7 dropped that hook
		inlineSvg(
			[
				{
					directories: path.resolve(projectRoot, 'static/icons'),
					attributes: {
						class: 'icon'
					}
				}
			],
			{ typedef: true }
		)
	],
	kit: {
		adapter: adapter()
	}
};

export default config;
