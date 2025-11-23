<script lang="ts">
	import type { PageData } from './$types';
	export let data: PageData;
	const formatted_date = (date: Date) =>
		`${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
</script>

<svelte:head>
	<title>Westrom.xyz - Blog Posts</title>
</svelte:head>

<h1>Blog</h1>
<ul class="blogposts">
	{#each data.posts as blogpost}
		<li class="preview-container">
			<a href="/blog/{blogpost.filepath}">
				<h2>{blogpost.title}</h2>
				{#if blogpost.date}
					<p class="color-2">{formatted_date(new Date(blogpost.date))}</p>
				{/if}
				<p class="subtitle color-2">{blogpost.subtitle}</p>
			</a>
			<!-- TODO: Make these tags actually do something on the page -->
			{#if blogpost.tags}
				<ul class="tags color-2">
					{#each blogpost.tags as tag}
						<li class="tag">
							<a href="/blog/?tag={tag}">{tag}</a>
						</li>
					{/each}
				</ul>
			{:else}
				No tags
			{/if}
		</li>
	{/each}
</ul>

<style>
	ul.blogposts > li.preview-container:last-child {
		border: none;
	}

	.preview-container {
		padding-bottom: 1em;
		border: solid var(--text-color);
		border-width: 0;
		border-bottom-width: 1px;
		margin-bottom: 1.5em;
	}

	a {
		color: hsl(235 80% 60%);
		@media (prefers-color-scheme: dark) {
			color: hsl(215 100% 75%);
		}

		h2 {
			text-decoration: underline;
			transition: all 0.1s ease-out;
		}

		h2:hover {
			text-decoration-color: transparent;
		}
		p {
			line-height: 1.6;
		}
	}

	.color-2 {
		color: var(--text-secondary);
		border-color: var(--text-secondary);
	}

	.tags {
		display: inline-flex;
		flex-wrap: wrap;
		padding-bottom: 0;
	}

	.tag {
		margin: 0.2rem;
		border-style: solid;
		border-width: 1px;
		border-radius: 0.25em;
		padding-left: 0.5em;
		padding-right: 0.5em;
		padding: 0.25rem;
	}
</style>
