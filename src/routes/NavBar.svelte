<nav class="border-current">
	<button class="hamburger">
		<svg viewBox="0 0 100 100" stroke="currentColor" stroke-width="10" stroke-linecap="round">
			<line x1="10" x2="90" y1="20" y2="20" />
			<line x1="10" x2="90" y1="50" y2="50" />
			<line x1="10" x2="90" y1="80" y2="80" />
		</svg>
	</button>
	<ul class="nav-items">
		<li>
			<a href="/">Home</a>
		</li>
		<li>
			<a href="/contact">Contact</a>
		</li>
		<li>
			<a href="/portfolio">Portfolio</a>
		</li>
		<li>
			<a href="/blog">Blog</a>
		</li>
	</ul>
</nav>

<style>
	.hamburger {
		background-color: inherit;
		padding: 0.75em;
		width: 4rem;
		height: 4rem;
		border-width: 0;
		display: none;
		color: var(--text-color);

		@media (max-width: 639px) {
			display: block;
			padding: 0;
			&:hover svg {
				line {
					y1: 50;
					y2: 50;
				}
			}
		}
	}

	@media (max-width: 639px) {
		.nav-items {
			display: none;
		}

		:hover {
			display: block;
		}
	}

	nav {
		background-color: var(--bg-secondary);
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		margin-bottom: 1rem;
		padding: 0.75em;

		@media (max-width: 639px) {
			position: fixed;
			bottom: 2rem;
			left: 0;
			right: 0;
			z-index: 5;
			max-width: fit-content;
			margin: auto;
			border-radius: 0.5rem;

			:hover {
				border-radius: 0.5rem;
			}
		}
	}

	ul {
		display: flex;
		flex-wrap: wrap;
		row-gap: 0.5rem;
		column-gap: 2rem;
		margin: auto;
	}
</style>
