import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';
import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

/** @type {import('vite').Plugin} */
const copyBlogImages = {
	name: 'copy-blog-images',
	buildStart() {
		const sourceDir = path.join(__dirname, 'posts', 'images');
		const targetDir = path.join(__dirname, 'static', 'blog', 'images');

		// Create target directory if it doesn't exist
		if (!fs.existsSync(targetDir)) {
			fs.mkdirSync(targetDir, { recursive: true });
		}

		// Copy all files from source to target
		if (fs.existsSync(sourceDir)) {
			const files = fs.readdirSync(sourceDir);

			for (const file of files) {
				const sourcePath = path.join(sourceDir, file);
				const targetPath = path.join(targetDir, file);

				// Only copy files (not directories)
				if (fs.statSync(sourcePath).isFile()) {
					fs.copyFileSync(sourcePath, targetPath);
					console.log(`[copy-blog-images] Copied ${file} to static/blog/images/`);
				}
			}
		} else {
			console.warn(`[copy-blog-images] Warning: ${sourceDir} does not exist`);
		}
	}
};

const config = defineConfig({
	plugins: [copyBlogImages, sveltekit()],
	server: {
		watch: {
			ignored: ['**/.direnv/**', '**/.devenv/**']
		}
	}
});

export default config;
