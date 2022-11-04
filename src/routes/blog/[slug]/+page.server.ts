import type { PageServerLoad } from './$types';
import fs from 'fs';
import path from 'path';
import { processOrg } from '$lib/process-org';
import { error } from '@sveltejs/kit';

export const load: PageServerLoad = ({ params }) => {
	const slug = params.slug;
	const posts = fs
		.readdirSync(`./posts`)
		.filter((fileName) => path.extname(fileName) == '.org')
		.map((fileName) => {
			return processOrg(`./posts/${fileName}`);
		});

	const blog_post = posts.filter((post): boolean => {
		const idToBeMatched = post.data.filePath;
		return slug == idToBeMatched;
	})[0];

	if (blog_post) {
		// Hack to get around node 16 not having `structuredClone()`.
		return JSON.parse(JSON.stringify(blog_post));
	}

	throw error(404, {
		message: 'Not found'
	});
};
