{
  inputs = {
    flake-utils.url = "github:numtide/flake-utils";
    devenv.url = "github:cachix/devenv";
  };

  outputs =
    {
      nixpkgs,
      flake-utils,
      devenv,
      ...
    }@inputs:
    flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = nixpkgs.legacyPackages.${system};
      in
      {
        devShell = devenv.lib.mkShell {
          inherit inputs pkgs;
          modules = [
            (
              { pkgs, ... }:
              {
                # packages = with pkgs; [ ];
                languages.javascript = {
                  enable = true;
                  package = pkgs.nodejs-slim_latest;
                  bun.enable = true;
                };
              }
            )
          ];
        };
      }
    );
}
