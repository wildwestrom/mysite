import fs from 'fs';
import path from 'path';
import { processOrg } from '$lib/process-org';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async () => {
	const posts = fs
		.readdirSync(`./posts`)
		.filter((fileName) => path.extname(fileName) == '.org')
		.map((fileName) => {
			const post = processOrg(`./posts/${fileName}`);
			return post.data;
		})
		.reverse();

	return { posts };
};
