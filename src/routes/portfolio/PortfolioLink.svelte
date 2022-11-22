<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	export let href: string | undefined = '';
	export let target: string | undefined = '_blank';
	export let icon: string;
</script>

<li>
	<ExternalLink {href} {target}>
		<svg><use href="/icons/{icon}.svg#icon"></use></svg><slot/>
	</ExternalLink>
</li>

<style lang="postcss">
	svg {
		margin-right: 0.25rem;
		width: 1rem;
		height: 1rem;
	}
</style>
