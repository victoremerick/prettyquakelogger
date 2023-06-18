import React, {useState, useEffect} from 'react';
import GameDetail from "./component/GameDetail";
import {Stack} from "@mui/material";
import fetchGames from "./service/GameService";

const App = () => {

const [games, setGames] = useState({});

    useEffect(() => {
        fetchGames(setGames);
    }, []);

    const keys = Object.keys(games);

    const result = keys.map(key => {
        return <GameDetail gameName={key} gameData={games[key]} />;
    })

      return (
          <>
              <h1 style={{textAlign:"center"}}>Pretty Quake Logger</h1>
              <Stack style={{width: "600px", marginLeft: "auto", marginRight: "auto"}}>
                  {result}
              </Stack>
          </>
      )
};

export default App;