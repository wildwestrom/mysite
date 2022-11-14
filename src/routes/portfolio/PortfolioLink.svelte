<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	export let href: string | undefined = '';
	export let target: string | undefined = '_blank';
	export let icon: string;
	const icon_alt_text = (icon: string): string => {
		switch (icon) {
			case 'git-alt':
				return 'Git';
			case 'globe':
				return 'Globe';
			default:
				throw new Error('Did not recognize icon');
		}
	};
</script>

<li class="portfolio-link-container">
	<ExternalLink {href} {target}
		><img alt="{icon_alt_text(icon)} icon" src="/icons/{icon}.svg" />{href}</ExternalLink
	>
</li>

<style>
	img {
		margin-right: 0.25rem;
		width: 1rem;
	}
	:global(.portfolio-link-container > *) {
		display: inline-flex;
	}
</style>
