<script lang="ts" context="module">
	import type { LoadInput } from '@sveltejs/kit'
	import type { BlogPost } from 'src'

	export async function load({
		params,
		fetch
	}: LoadInput): Promise<{ status: Number; props: { blogPost: BlogPost } }> {
		const id = params.id
		const url = `/blog/${id}.json`
		const res = await fetch(url)

		return {
			status: res.status,
			props: {
				blogPost: await res.json()
			}
		}
	}
</script>

<script lang="ts">
	export let blogPost: BlogPost
</script>

<svelte:head>
	<title>{blogPost.data.title} - Westrom.xyz - Blog</title>
	<meta name="description" content={blogPost.data.description} />
</svelte:head>

<article>
	<h1 class="section-header">{blogPost.data.title}</h1>
	{@html blogPost.value}
</article>

<style>
	article :global {
		--code-bg: hsl(0, 0%, 90%);

		@media (prefers-color-scheme: dark) {
			--code-bg: hsl(0, 0%, 15%);
		}

		p {
			margin: 1rem 0;
		}

		h1,
		h2,
		h3,
		h4 {
			line-height: 1.1;
			margin: 1rem 0;
		}

		h1 {
			font-size: 2rem;
			font-weight: 800;
		}

		h2 {
			font-size: 1.75rem;
			font-weight: 600;
		}

		h3 {
			font-size: 1.5rem;
			font-weight: 600;
		}

		pre {
			overflow: scroll;
			background-color: var(--code-bg);
		}

		code {
			background-color: var(--code-bg);
		}

		a {
			text-decoration: underline;

			&:hover {
				color: var(--hover-color);
				text-decoration: none;
			}
		}
	}
</style>
