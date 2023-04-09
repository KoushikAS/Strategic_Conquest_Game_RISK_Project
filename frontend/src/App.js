import "bootstrap/dist/css/bootstrap.min.css";
import "./styles/App.css";
import { AuthProvider, AuthContext } from "./auth/AuthProvider";

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import GameView from "./game/GameView";
import AttackView from "./game/AttackView";
import LoginView from "./auth/LoginView";
import GameListView from "./game_list/GameListView";
import { ProtectedRoute } from "./auth/ProtectedRoute";

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
          <Route
            exact
            path="/"
            element={
              // <ProtectedRoute>
                <GameView />
              // </ProtectedRoute>
            }
          />
          <Route
            exact
            path="/attack"
            element={
              // <ProtectedRoute>
                <AttackView />
              // </ProtectedRoute>
            }
          />
          <Route path="/login" element={<LoginView />} />
        </Routes>
      </AuthProvider>
    </Router>
  );
}

export default App;
