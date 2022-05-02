<script lang="ts">
	import type { BlogPost } from 'src'
	export let blogPost: BlogPost
</script>

<a href="/blog/{blogPost.data.id}">
	<div>
		<h2>{blogPost.data.title}</h2>
		<h3>{blogPost.data.subtitle}</h3>
	</div>
</a>

<style>
	a {
		div {
			margin: 0.5em 0;
			border-style: solid;
			border-width: 2px;
			border-radius: 0.5em;
			padding-left: 1em;
			padding-right: 1em;
			padding-bottom: 0.5rem;

			h3 {
				font-weight: 400;
				line-height: 1.1;
			}
		}
	}
</style>
