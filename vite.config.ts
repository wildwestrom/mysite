import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';

const config = defineConfig({
	plugins: [sveltekit()],
	server: {
		watch: {
			ignored: ['**/.direnv/**', '**/.devenv/**']
		}
	}
});

export default config;
