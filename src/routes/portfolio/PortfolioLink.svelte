<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';

	interface Props {
		href?: string | undefined;
		target?: string | undefined;
		icon?: import('svelte').Snippet;
		children?: import('svelte').Snippet;
	}

	let { href = '', target = '_blank', icon, children }: Props = $props();
</script>

<li class="portfoliolink_container">
	<ExternalLink {href} {target} isPortfolioLink>
		{#if icon}
			{@render icon()}
		{/if}
		<span id="content"
			>{@render children?.()}<span>
				<svg inline-src="external-link" aria-hidden="true" class="after" focusable="false" />
			</span></span
		></ExternalLink
	>
</li>

<style>
	:global(li.portfoliolink_container > .portfolio) {
		display: flex;
		grid-auto-flow: column;
		align-items: center;
		margin: 0.25rem 0;
	}
	svg {
		width: 1.5em;
		height: 1.5em;

		&.after {
			align-self: flex-start;
			width: 1em;
			height: 1em;
		}
	}
</style>
