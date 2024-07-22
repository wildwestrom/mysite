<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	export let href: string | undefined = '';
	export let target: string | undefined = '_blank';
	export let icon: string;
</script>

<li class="portfoliolink_container">
	<ExternalLink {href} {target} isPortfolioLink>
		<img src="/icons/{icon}.svg" class="before" alt="icon" />
		<span id="content"
			><slot /><span>
				<img src="/icons/external-link.svg" class="after" alt="link icon" />
			</span></span
		></ExternalLink
	>
</li>

<style lang="postcss">
	:global(li.portfoliolink_container > .portfolio) {
		display: flex;
		grid-auto-flow: column;
		align-items: center;
		margin: 0.25rem 0;
	}

	img {
		width: 1.5em;
		height: 1.5em;

		&.before {
			margin-right: 0.25rem;
		}

		&.after {
			align-self: flex-start;
			width: 1em;
			height: 1em;
		}
	}
</style>
