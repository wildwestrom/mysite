<script lang="ts">
	export let href: string | undefined = '';
	export let target: string | undefined = '_blank';
	export let isPortfolioLink: boolean | undefined = false;
</script>

<a class={isPortfolioLink ? 'portfolio' : ''} {href} {target} rel="noreferrer"><slot /></a>

<style lang="postcss">
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
