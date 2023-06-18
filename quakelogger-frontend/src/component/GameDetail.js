import React from 'react';
import {Box, Grid} from "@mui/material";
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: '#1A2027',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: '#b4b4b4',
}));

const GameDetail = ({gameName, gameData}) => {

    function createTable() {
        if(Object.keys(gameData.kills).length === 0) return "-";

        return (
            <TableContainer component={Paper}>
                <Table style={{background: '#1A2027'}} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell style={{fontWeight: "bold", color: "#b4b4b4"}}>Player</TableCell>
                            <TableCell align="right" style={{fontWeight: "bold", color: "#b4b4b4"}}>Kills</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {Object.keys(gameData.kills).map(key => (
                            <TableRow>
                                <TableCell style={{color: "#b4b4b4"}}>{key}</TableCell>
                                <TableCell align="right" style={{color: "#b4b4b4"}}>{gameData.kills[key]}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>);
    }

    return (
        <Box sx={{ flexGrow: 1 }} style={{margin: "2em"}}>
            <Grid container spacing={2}>
                <Grid item xs={12}>
                    <Item>{gameName}</Item>
                </Grid>
                <Grid item xs={4}>
                    <Item>Total Kills</Item>
                </Grid>
                <Grid item xs={8}>
                    <Item>{gameData.total_kills}</Item>
                </Grid>
                <Grid item xs={4}>
                    <Item>Players</Item>
                </Grid>
                <Grid item xs={8}>
                    <Item>{gameData.players.length === 0 ? "-" : gameData.players.map(player =>(<p>{player}</p>))}</Item>
                </Grid>
                <Grid item xs={4}>
                    <Item>Kills</Item>
                </Grid>
                <Grid item xs={8}>
                    <Item>
                        {createTable()}
                    </Item>
                </Grid>
            </Grid>
        </Box>
    );
}

export default GameDetail;