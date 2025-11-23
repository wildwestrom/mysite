<script lang="ts">
	interface Props {
		href?: string | undefined;
		target?: string | undefined;
		isPortfolioLink?: boolean | undefined;
		children?: import('svelte').Snippet;
	}

	let {
		href = '',
		target = '_blank',
		isPortfolioLink = false,
		children
	}: Props = $props();
</script>

<a class={isPortfolioLink ? 'portfolio' : ''} {href} {target} rel="noreferrer">{@render children?.()}</a>

<style>
	a {
		text-decoration: underline;
		cursor: pointer;
		transition: all 0.1s ease-out;
	}

	a:hover {
		color: var(--hover-color);
		text-decoration-color: transparent;
	}
</style>
