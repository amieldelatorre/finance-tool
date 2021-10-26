import React from 'react';
import { NavBar } from '../common/NavBar';
import CssBaseLine from '@mui/material/CssBaseline';
import { ThemeProvider, createTheme } from '@mui/material/styles'

const themeLight = createTheme({
    palette: {
      background: {
        default: "#e4f0e2"
      }
    }
  });
  
  const themeDark = createTheme({
    palette: {
      background: {
        default: "#222222"
      },
      text: {
        primary: "#ffffff"
      }
    }
  });

export const Layout = (props) => {
    const [light, setTheme] = React.useState(false);

    return (
        <ThemeProvider theme={light ? themeLight : themeDark }>
            <CssBaseLine />
            <NavBar setTheme={() => {setTheme(!light)}} light={light}/>
            {props.children}
        </ThemeProvider>
    )
}

export default Layout;
