import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Container from "@mui/material/Container";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import { NavLink } from "react-router-dom";
import { rutesServeis } from "../../router/rutesServeis";
import "./Navbar.css";
import ToggleColor from "../toggleColor/ToggleColor";

const Navbar = () => {
  const [anchorElNav, setAnchorElNav] = React.useState(null);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  return (
    <AppBar id="appBar" className="barraSuperior" position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          {/* TÍTOL PANTALLES GRANS*/}
          <Typography
            component={NavLink}
            to="/"
            color="white"
            variant="h6"
            noWrap
            sx={{
              paddingRight: "3rem",
              mr: 2,
              display: { xs: "none", md: "flex" },
              fontFamily: "Segoe UI",
              fontWeight: 600,
              color: "inherit",
              textDecoration: "none",
            }}
          >
            PinbalMonitor
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton
              size="large"
              aria-label="Obrir menú"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>
            {/* MENU PANTALLES PETITES*/}
            <Menu
              className="pagines"
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "bottom",
                horizontal: "left",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "left",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: "block", md: "none" },
              }}
            >
              {rutesServeis.map((pagina) => (
                <MenuItem
                  component={NavLink}
                  to={pagina.path}
                  key={pagina.nomAMenu}
                  onClick={handleCloseNavMenu}
                >
                  <Typography color="var(--secundari)" textAlign="center">
                    {pagina.nomAMenu}
                  </Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
          {/* TÍTOL PANTALLES PETITES*/}

          <Typography
            variant="h5"
            noWrap
            component={NavLink}
            to="/"
            sx={{
              mr: 2,
              display: { xs: "flex", md: "none" },
              flexGrow: 1,
              fontFamily: "Segoe UI",
              fontWeight: 600,
              color: "inherit",
              textDecoration: "none",
            }}
          >
            PinbalMonitor
          </Typography>
          {/* MENU PANTALLES GRANS*/}

          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            {rutesServeis.map((pagina) => (
              <Button
                size="large"
                component={NavLink}
                to={pagina.path}
                key={pagina.nomAMenu}
                onClick={handleCloseNavMenu}
                sx={{ my: 2, color: "white", display: "block" }}
              >
                {pagina.nomAMenu}
              </Button>
            ))}
          </Box>
          <Box sx={{  display: { md: "flex" } }}>
            <ToggleColor
              sx={{ my: 2, color: "white", display: "block" }}
            ></ToggleColor>
          </Box>

          {/*<Box sx={{ flexGrow: 0 }}>
            <Button size="large" color="inherit">
              Tancar sessió
            </Button>
          </Box>*/}
        </Toolbar>
      </Container>
    </AppBar>
  );
};
export default Navbar;
