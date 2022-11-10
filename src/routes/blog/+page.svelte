<script lang="ts">
	import type { PageData } from './$types';
	export let data: PageData;

</script>

<svelte:head>
	<title>Westrom.xyz - Blog Posts</title>
</svelte:head>

<h1>Blog</h1>
<div>
	<ul class="blogposts">
		{#each data.posts as blogpost}
			<li>
				<div class="preview-container">
					<a href="/blog/{blogpost.filePath}">
						<h2>{blogpost.title}</h2>
						<p class="subtitle color-2">{blogpost.subtitle}</p>
					</a>
					{#if blogpost.tags}
						<ul class="tags color-2">
							{#each blogpost.tags.split(' ') as tag}
								<li class="tag">
									<a href="/blog/?tag={tag}">{tag}</a>
								</li>
							{/each}
						</ul>
					{:else}
						No tags
					{/if}
				</div>
			</li>
		{/each}
	</ul>
</div>

<style lang="postcss">
	ul.blogposts > li:last-child .preview-container {
		border: none;
	}

	.preview-container {
		font-size: 0.8rem;
		padding-bottom: 1em;
		margin: 0.5em;
		line-height: 1.1;
		border-color: var(--text-color);
		border-bottom-width: 1px;
		margin-bottom: 0.75em;
	}

	a {
		color: hsl(235 80% 60%);
		p {
			padding-bottom: .75em;
		}
	}

	h2 {
		font-size: 1rem;
		font-weight: 400;
		text-decoration: underline;
		padding-bottom: .75em;
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
