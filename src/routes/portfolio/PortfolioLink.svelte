<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	export let href: string | undefined = '';
	export let target: string | undefined = '_blank';
	export let icon: string;
</script>

<li class="portfoliolink_container">
	<ExternalLink {href} {target} isPortfolioLink>
		<svg class="before"><use href="/icons/{icon}.svg#icon" /></svg><slot /><svg class="after"
			><use href="/icons/external-link.svg#icon" /></svg
		>
	</ExternalLink>
</li>

<style lang="postcss">
	:global(li.portfoliolink_container > a.portfolio) {
		display: flex;
		grid-auto-flow: column;
		align-items: center;
		margin: 0.25rem 0;
	}

	svg {
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
