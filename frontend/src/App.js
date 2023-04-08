import "bootstrap/dist/css/bootstrap.min.css";
import "./styles/App.css";
import { AuthProvider, AuthContext } from "./AuthProvider";

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import GameView from "./game/GameView";
import AttackView from "./game/AttackView";
import LoginView from "./login/LoginView";
import GameListView from "./game_list/GameListView";
import { ProtectedRoute } from "./ProtectedRoute";

function App() {
  return (
    <Router>
      <AuthProvider>
        <Routes>
          <Route
            exact
            path="/gameList"
            element={
              <ProtectedRoute>
                <GameListView />
              </ProtectedRoute>
            }
          />
          <Route exact path="/" element={<GameView />} />
          <Route exact path="/attack" element={<AttackView />} />
          <Route path="/login" element={<LoginView />} />
        </Routes>
      </AuthProvider>
    </Router>
  );
}

export default App;
