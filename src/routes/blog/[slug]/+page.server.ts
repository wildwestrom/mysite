import type { PageServerLoad } from './$types';
import fs from 'fs';
import path from 'path';
import { processMd } from '$lib/processMd';
import { error } from '@sveltejs/kit';

export const load: PageServerLoad = ({ params }) => {
	const slug = params.slug;
	const posts = fs
		.readdirSync(`./posts`)
		.filter((fileName) => path.extname(fileName) == '.md')
		.map((fileName) => {
			return processMd(`./posts/${fileName}`);
		});

	const blog_post = posts.filter((post): boolean => {
		const idToBeMatched = post.data.filepath;
		return slug == idToBeMatched;
	})[0];

	if (blog_post) {
		// Hack to get around node 16 not having `structuredClone()`.
		let post = JSON.parse(JSON.stringify(blog_post));
		return post;
	}

	throw error(404, {
		message: 'Not found'
	});
};
