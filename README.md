``mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#E3F2FD', 'edgeLabelBackground':'#ffffff', 'tertiaryColor': '#fff', 'actorBorder': '#1976D2', 'actorBkg': '#E3F2FD', 'ellipseBorder': '#1976D2', 'ellipseBkg': '#E3F2FD'}}}%%
graph LR
    %% Título del Diagrama (Fuera del recuadro del sistema)
    title[Diagrama UML de Casos de Uso Vertical]
    style title fill:none,stroke:none,font-weight:bold,font-size:16px;

    %% Límite del Sistema
    subgraph "Sistema de Gestión Inmobiliaria"
        direction TB

        %% Casos de Uso
        uc_informes(Generación de Informes)
        uc_inquilinos(Gestión de Inquilinos)
        uc_recibos(Generación Mensual de Recibos)
        uc_bancos(Registro de Movimientos Bancarios)
        uc_inmuebles(Gestión de Inmuebles)
        uc_alquileres(Gestión de Alquileres y Desalquileres)

    end

    %% Actores (Símbolos estándar de hombre de palo)
    actor_secretario[<div style='text-align:center'> <img src='https://upload.wikimedia.org/wikipedia/commons/e/e0/Stick_figure.svg' width='40' height='40' /> <br/> Secretario </div>]
    actor_inquilino[<div style='text-align:center'> <img src='https://upload.wikimedia.org/wikipedia/commons/e/e0/Stick_figure.svg' width='40' height='40' /> <br/> Inquilino </div>]

    %% Estilos de Actores
    style actor_secretario fill:#BBDEFB,stroke:#1976D2,stroke-width:2px;
    style actor_inquilino fill:#BBDEFB,stroke:#1976D2,stroke-width:2px;

    %% Relaciones Actor -> Caso de Uso
    actor_secretario --> uc_informes
    actor_secretario --> uc_inquilinos
    actor_secretario --> uc_recibos
    actor_secretario --> uc_bancos
    actor_secretario --> uc_inmuebles
    actor_secretario --> uc_alquileres

    actor_inquilino --> uc_alquileres

    %% Estilo del subgrafo del sistema
    style "Sistema de Gestión Inmobiliaria" fill:#F5F5F5,stroke:#FF9800,stroke-width:3px,color:#333;
``
