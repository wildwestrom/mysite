import fs from 'fs';
import path from 'path';
import { processMd } from '$lib/processMd';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async () => {
	const posts = fs
		.readdirSync(`./posts`)
		.filter((fileName) => path.extname(fileName) == '.md')
		.map((fileName) => {
			const post = processMd(`./posts/${fileName}`);
			return post.data;
		})
		.reverse();

	return { posts };
};
