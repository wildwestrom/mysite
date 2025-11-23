<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	import type { SVGAttributes } from 'svelte/elements';

	interface Props {
		href: string;
		name: string;
		icon: SVGAttributes<SVGElement>['inline-src'];
	}

	let { href, name, icon }: Props = $props();
</script>

<li>
	<span>{name}</span>
	<ExternalLink {href}>
		{#if icon === 'github'}
			<svg inline-src="github" aria-hidden="true" class="icon" focusable="false" />
		{:else if icon === 'hackerspub'}
			<svg inline-src="hackerspub" aria-hidden="true" class="icon" focusable="false" />
		{:else if icon === 'matrix'}
			<svg inline-src="matrix" aria-hidden="true" class="icon" focusable="false" />
		{:else if icon === 'envelope'}
			<svg inline-src="envelope" aria-hidden="true" class="icon" focusable="false" />
		{:else if icon === 'discord'}
			<svg inline-src="discord" aria-hidden="true" class="icon" focusable="false" />
		{/if}
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

	svg.icon {
		width: 4rem;
		height: 4rem;
		margin: 0 1rem;
	}
</style>
