<script lang="ts">
	import type { BlogPostMetadata } from 'src';
	export let blogpost: BlogPostMetadata;
	let tags = blogpost.tags?.split(' ');
</script>

<div class="preview-container">
	<a href="/blog/{blogpost.filePath}">
		<h2>{blogpost.title}</h2>
		<p class="subtitle color-2">{blogpost.subtitle}</p>
	</a>
	{#if tags}
		<ul class="tags color-2">
			{#each tags as tag}
				<li class="tag">
					<a href="/blog/?tag={tag}">{tag}</a>
				</li>
			{/each}
		</ul>
	{:else}
		No tags
	{/if}
</div>

<style>
	* {
		padding-bottom: 0.75em;
		font-size: 0.8rem;
	}

	.preview-container {
		margin: 0.5em;
		line-height: 1.1;
		border-color: var(--text-color);
		border-bottom-width: 1px;
		margin-bottom: 0.75em;
	}

	a {
		color: hsl(235 80% 60%)
	}

	h2 {
		font-size: 1rem;
		font-weight: 400;
		text-decoration: underline;
	}

	h2:hover {
		text-decoration: none;
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
