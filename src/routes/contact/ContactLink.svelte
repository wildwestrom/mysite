<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	export let href: string;
	export let name: string;
	export let icon: string;
</script>

<li>
	<span>{name}</span>
	<ExternalLink {href}>
		<svg><use href="/icons/{icon}.svg#icon" /></svg>
	</ExternalLink>
</li>

<style lang="postcss">
	li {
		display: grid;
		align-items: center;
		grid-template-columns: 1fr 1fr;
		gap: 2rem;
		justify-items: center;
	}

	svg {
		align-self: center;
		width: 4rem;
		height: 4rem;
		margin: 0 1rem;
	}
</style>
