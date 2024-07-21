{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    devenv.url = "github:cachix/devenv";
  };

  outputs =
    {
      self,
      nixpkgs,
      flake-utils,
      devenv,
    }@inputs:
    flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = import nixpkgs { inherit system; };
      in
      {
        formatter = pkgs.alejandra;

        devShell = devenv.lib.mkShell {
          inherit inputs pkgs;
          modules = [
            (
              { pkgs, config, ... }:
              {
                # packages = with pkgs; [ ];
                languages.javascript = {
                  enable = true;
                  bun.enable = true;
                };
              }
            )
          ];
        };
      }
    );
}
