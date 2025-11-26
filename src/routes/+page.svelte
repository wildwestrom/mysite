<script lang="ts">
	import { onMount } from 'svelte';

	let showTooltip = $state(false);
	let showHoverStyles = $state(false);
	let timer: ReturnType<typeof setTimeout> | null = null;
	let hoverTimer: ReturnType<typeof setTimeout> | null = null;

	onMount(() => {
		timer = setTimeout(() => {
			showTooltip = true;
			timer = null;

			// Apply hover styles 5 seconds after tooltip appears
			hoverTimer = setTimeout(() => {
				showHoverStyles = true;
				hoverTimer = null;
			}, 5 * 1000);
		}, 8 * 1000);

		return () => {
			if (timer) clearTimeout(timer);
			if (hoverTimer) clearTimeout(hoverTimer);
		};
	});
</script>

<svelte:head>
	<title>Westrom.xyz - Home</title>
</svelte:head>

<h1>Welcome to my site!</h1>

<a
	id="money-button"
	href="https://ko-fi.com/E1E61P5C8X"
	target="_blank"
	class:auto-hover={showHoverStyles}
>
	<h2 class="strong">Give Me Your Money</h2>
	<div class="tooltip" class:visible={showTooltip}>
		<p>
			No, really!<br />
			I'm not offering <span class="strong">anything</span>.
		</p>
		<p>
			This is literally just <br />
			a <span class="emph">"give me money"</span> button,<br />
			for <span class="strong">you</span>, to give me <span class="strong">your money</span>,<br />
			for no reason at all.
		</p>
	</div>
</a>

<style>
	:root {
		--tooltip-transition-duration: 3s;
	}

	#money-button {
		display: flex;
		flex-direction: column;
		align-items: center;
		gap: 0;
		margin: 3rem auto;
		width: fit-content;
		min-width: 320px;
		text-align: center;
		border: 2px solid;
		padding: 1.5rem;
		border-radius: 0.75rem;
		box-shadow: 0.7rem 0.7rem 0 var(--bg-secondary);
		overflow: hidden;
		transition: 
			/* for hovering */
			box-shadow 0.05s ease,
			background-color 0.1s ease,
			color 0.1s ease,
			transform 0.05s ease,
			/* for tooltip */ max-width var(--tooltip-transition-duration) ease;
		text-decoration: none;
		&:hover {
			transform: translate(0.15rem, 0.15rem);
			box-shadow: 0.4rem 0.4rem 0 var(--bg-secondary);
		}

		&:hover,
		&.auto-hover {
			background-color: #feffe7;
			color: #202819;
		}
	}

	#money-button h2 {
		white-space: nowrap;
		margin: 0;
	}

	#money-button:hover h2,
	#money-button.auto-hover h2 {
		color: #4f835c;
	}

	.strong {
		font-weight: bold;
	}

	.emph {
		font-style: italic;
	}

	.tooltip {
		max-height: 0;
		overflow: hidden;
		width: 100%;
		text-align: center;
		transition:
			max-height var(--tooltip-transition-duration) ease,
			margin-top calc(var(--tooltip-transition-duration) / 2) ease,
			opacity calc(var(--tooltip-transition-duration) / 2) ease;
		margin-top: 0;
		opacity: 0;
	}

	.tooltip.visible {
		max-height: 500px;
		margin-top: 1rem;
		opacity: 1;
	}

	.tooltip p {
		margin: 0.5rem 0;
		line-height: 1.8;
	}

	#money-button:has(.tooltip.visible) {
		max-width: min(65ch, 75vw);
	}
</style>
