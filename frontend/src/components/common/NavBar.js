import React from 'react';
import { AppBar, Toolbar, Typography, IconButton, Grid, Button, Tooltip, MenuItem, Menu } from '@mui/material';
import Brightness7Icon from '@mui/icons-material/Brightness7';
import Brightness3Icon from '@mui/icons-material/Brightness3';
import { Link } from 'react-router-dom';
import { makeStyles } from '@mui/styles';

const useStyles = makeStyles((theme) => ({
    header: {
        backgroundColor: "#45A3E8",
        display: 'flex'
    },
    title: {
        fontSize: "28px",
        fontWeight: "650px"
    },
    menuButton: {
        fontWeight: 550,
        fontSize: "18px",
        '&:hover': {
            backgroundColor: 'transparent',
            borderBottomWidth: 1,
            borderColor: '#9c34eb',
            borderStyle: 'solid',
            borderRadius: 0
        }
    },
    lightSwitch: { 
        flex: 1
    },
    menu: {
        "& .MuiPaper-root": {
            backgroundColor: "#45A3E8",
          }
    },
}));

export const NavBar = (props) => {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const classes = useStyles();

    const getLightIcon = () => {
        return (
            props.light ? <Brightness3Icon /> : <Brightness7Icon />
        )
    }

    function handleClick(event) {
        if (anchorEl !== event.currentTarget) {
          setAnchorEl(event.currentTarget);
        }
      }
    
      function handleClose() {
        setAnchorEl(null);
      }

    return (
        <header>
            
            <AppBar className={classes.header} position="static">
                <Toolbar className={classes.toolbar}>

                    <Grid container direction="row" spacing={5}>
                        <Grid item key="logo">
                            <Typography variant="h6" className={classes.title}>
                                A. DT
                            </Typography>
                        </Grid>
                        
                        <Grid item key="home">
                            <Button {...{
                                key: "home", 
                                color: "inherit",
                                to: "/",
                                component: Link,
                                className: classes.menuButton
                            }}>
                                Home
                            </Button>
                        </Grid>

                        <Grid item key="projects">
                            <Button {...{
                                key: "projects", 
                                color: "inherit",
                                to: "/projects",
                                component: Link,
                                className: classes.menuButton
                            }} 
                                aria-owns={anchorEl ? "projects-menu" : undefined}
                                aria-haspopup="true"
                                onMouseOver={handleClick}>
                                Projects
                            </Button>
                        </Grid>
                        <Menu 
                            className={classes.menu}
                            id="projects-menu"
                            anchorEl={anchorEl}
                            open={Boolean(anchorEl)}
                            onClose={handleClose}
                            MenuListProps={{ onMouseLeave: handleClose }}
                        >   
                            <MenuItem onClick={handleClose} to="/projects" component={Link}>Projects</MenuItem>
                            <MenuItem onClick={handleClose} to="/projects/web" component={Link}>Web Applications</MenuItem>
                            <MenuItem onClick={handleClose} to="/projects/desktop" component={Link}>Desktop Applications</MenuItem>
                        </Menu>

                        <Grid item key="contact">
                            <Button {...{
                                key: "contact", 
                                color: "inherit",
                                to: "/contact",
                                component: Link,
                                className: classes.menuButton
                            }}>
                                Contact
                            </Button>
                        </Grid>

                    </Grid>

                    <IconButton onClick={props.setTheme} className={classes.lightSwitch}>
                        <Tooltip title="Toggle light/dark theme">
                            {getLightIcon()}
                        </Tooltip>
                    </IconButton>
                </Toolbar>
            </AppBar>
        </header>
    )
}

export default NavBar;