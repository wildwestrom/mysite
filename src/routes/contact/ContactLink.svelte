<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';

	interface Props {
		href: string;
		name: string;
		children?: import('svelte').Snippet;
	}

	let { href, name, children }: Props = $props();
</script>

<li>
	<span>{name}</span>
	<ExternalLink {href}>
		{@render children?.()}
	</ExternalLink>
</li>

<style>
	li {
		display: grid;
		align-items: center;
		grid-template-columns: 1fr 1fr;
		gap: 2rem;
		justify-items: center;
	}
</style>
