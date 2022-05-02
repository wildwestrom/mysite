import fs from 'fs'
import path from 'path'
import { processOrg } from '$lib/process-org'
import type { LoadInput } from '@sveltejs/kit'
import type { BlogPost } from 'src'

export async function get({
	params
}: LoadInput): Promise<{ status: number; body: BlogPost }> {
	const id = params.id
	const posts = fs
		.readdirSync(`src/routes/blog/_posts`)
		.filter(fileName => path.extname(fileName) == '.org')
		.map(fileName => {
			return processOrg(`src/routes/blog/_posts/${fileName}`)
		})

	const blogPost = posts.filter((post): boolean => {
		const idToBeMatched = post.data.id
		return id == idToBeMatched
	})[0]

	return {
		status: 200,
		body: blogPost
	}
}
