<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	export let href: string;
	export let name: string;
	export let icon: string;
	import iconAltText from '$lib/iconAltText';
</script>

<li class="contact-link-container" title={name}>
	<span>{name}</span><ExternalLink {href}
		><img alt="{iconAltText(icon)} icon" src="/icons/{icon}.svg" /></ExternalLink
	>
</li>

<style lang="postcss">
	:global(.contact-link-container > a) {
		display: contents;
	}

	li {
		display: grid;
		align-items: center;
		grid-template-columns: 1fr 1fr;
		gap: 25vw;
		justify-items: center;
		@media (max-width: 359px) {
			gap: 2rem;
		}
	}

	img {
		align-self: center;
		width: 4rem;
		margin: 0 1rem;
	}
</style>
