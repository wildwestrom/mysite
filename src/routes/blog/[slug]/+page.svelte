<script lang="ts">
	import type { PageData } from './$types';
	export let data: PageData;
</script>

<svelte:head>
	<title>{data.data.title} by Christian Westrom</title>
</svelte:head>

<h1>
	{data.data.title}
</h1>
<p class="subtitle">by Christian Westrom</p>

<article>
	{@html data.value}
</article>

<style lang="postcss">
	.subtitle {
		color: var(--text-secondary);
	}

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
		h4,
		h5,
		h6 {
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

		li {
			padding-left: 0.25em;
			margin-left: 1em;
		}

		ol {
			li {
				list-style: decimal;
			}
		}

		ul {
			li {
				list-style: disc;
			}
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
