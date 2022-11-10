<script lang="ts">
	import BlogPostPreviewCard from './BlogPostPreviewCard.svelte';
	import type { PageData } from './$types';
	export let data: PageData;
</script>

<svelte:head>
	<title>Westrom.xyz - Blog Posts</title>
</svelte:head>

<h1>Blog</h1>
<div>
	<ul>
		{#each data.posts as post}
			<li>
				<BlogPostPreviewCard blogpost={post} />
			</li>
		{/each}
	</ul>
</div>
