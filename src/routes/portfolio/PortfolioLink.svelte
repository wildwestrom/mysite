<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	import iconAltText from '$lib/iconAltText'
	export let href: string | undefined = '';
	export let target: string | undefined = '_blank';
	export let icon: string;
</script>

<li class="portfolio-link-container">
	<ExternalLink {href} {target}
		><img alt="{iconAltText(icon)} icon" src="/icons/{icon}.svg" />{href}</ExternalLink
	>
</li>

<style>
	:global(.portfolio-link-container > *) {
		display: inline-flex;
	}

	img {
		margin-right: 0.25rem;
		width: 1rem;
	}
</style>
