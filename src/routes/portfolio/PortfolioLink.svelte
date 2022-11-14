<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	export let href: string | undefined = '';
	export let target: string | undefined = "_blank";
	export let icon: string;
</script>

<li>
	<ExternalLink href={href} target={target}
		><svg class="portfolio-link-icon" width="1rem" height="1rem"><use xlink:href="#{icon}" /></svg>{href}</ExternalLink
	>
</li>

<style>
	:global(.portfolio-link-icon) {
		margin-right: 0.25rem;
	}
	svg {
		display: inline-block;
	}
</style>
