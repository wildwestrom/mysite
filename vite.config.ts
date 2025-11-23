import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig, type Plugin } from 'vite';
import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';
import inlineSvg from '@svelte-put/inline-svg/vite';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const SOURCE_DIR = path.resolve(__dirname, 'posts', 'images');
const BLOG_ROUTE = '/blog/images';
const DEFAULT_OUTPUT_DIR = path.resolve(__dirname, '.svelte-kit', 'output', 'client');
const CONTENT_TYPES: Record<string, string> = {
	'.png': 'image/png',
	'.jpg': 'image/jpeg',
	'.jpeg': 'image/jpeg',
	'.gif': 'image/gif',
	'.svg': 'image/svg+xml',
	'.webp': 'image/webp'
};

const serveDuringDev: Plugin['configureServer'] = (server) => {
	server.middlewares.use(BLOG_ROUTE, (req, res, next) => {
		if (!req.url || !fs.existsSync(SOURCE_DIR)) {
			return next();
		}

		const requestedFile = decodeURIComponent(req.url.split('?')[0] ?? '').replace(/^\/+/, '');
		const filePath = path.resolve(SOURCE_DIR, requestedFile);

		if (!filePath.startsWith(SOURCE_DIR)) {
			return next();
		}

		if (!fs.existsSync(filePath) || !fs.statSync(filePath).isFile()) {
			return next();
		}

		const ext = path.extname(filePath).toLowerCase();
		res.setHeader('Content-Type', CONTENT_TYPES[ext] ?? 'application/octet-stream');
		fs.createReadStream(filePath).pipe(res);
	});
};

const copyForBuild = (targetDir: string) => {
	if (!fs.existsSync(SOURCE_DIR)) {
		console.warn(`[copy-blog-images] Warning: ${SOURCE_DIR} does not exist`);
		return;
	}

	if (!fs.existsSync(targetDir)) {
		fs.mkdirSync(targetDir, { recursive: true });
	}

	for (const file of fs.readdirSync(SOURCE_DIR)) {
		const sourcePath = path.join(SOURCE_DIR, file);

		if (!fs.statSync(sourcePath).isFile()) {
			continue;
		}

		const targetPath = path.join(targetDir, file);
		fs.copyFileSync(sourcePath, targetPath);
		console.log(`[copy-blog-images] Copied ${file} to ${path.relative(__dirname, targetDir)}/`);
	}
};

const copyBlogImages: Plugin = {
	name: 'copy-blog-images',
	configureServer: serveDuringDev,
	writeBundle(options) {
		const outputDir = options.dir || DEFAULT_OUTPUT_DIR;
		const targetDir = path.join(outputDir, 'blog', 'images');
		copyForBuild(targetDir);
	}
};

const config = defineConfig({
	plugins: [
		copyBlogImages,
		inlineSvg(
			[
				{
					directories: path.resolve(__dirname, 'static/icons'),
					attributes: {
						class: 'icon'
					}
				}
			],
			{ typedef: true }
		),
		sveltekit()
	],
	server: {
		watch: {
			ignored: ['**/.direnv/**', '**/.devenv/**']
		}
	}
});

export default config;
