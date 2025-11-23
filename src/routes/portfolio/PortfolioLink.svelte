<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	import type { SVGAttributes } from 'svelte/elements';

	export let href: string | undefined = '';
	export let target: string | undefined = '_blank';
	export let icon: SVGAttributes<SVGElement>['inline-src'];
</script>

<li class="portfoliolink_container">
	<ExternalLink {href} {target} isPortfolioLink>
		{#if icon === 'git'}
			<svg
				inline-src="git"
				aria-hidden="true"
				class="before"
				focusable="false"
				height="24"
				width="24"
			/>
		{:else if icon === 'globe'}
			<svg
				inline-src="globe"
				aria-hidden="true"
				class="before"
				focusable="false"
				height="24"
				width="24"
			/>
		{/if}
		<span id="content"
			><slot /><span>
				<svg
					inline-src="external-link"
					aria-hidden="true"
					class="after"
					focusable="false"
					height="16"
					width="16"
				/>
			</span></span
		></ExternalLink
	>
</li>

<style>
	:global(li.portfoliolink_container > .portfolio) {
		display: flex;
		grid-auto-flow: column;
		align-items: center;
		margin: 0.25rem 0;
	}

	svg {
		width: 1.5em;
		height: 1.5em;

		&.before {
			margin-right: 0.25rem;
		}

		&.after {
			align-self: flex-start;
			width: 1em;
			height: 1em;
		}
	}
</style>
