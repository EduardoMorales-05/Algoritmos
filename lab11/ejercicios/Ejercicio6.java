public class Ejercicio6 {
    public static void main(String[] args) throws InterruptedException {
        SessionCache cache = new SessionCache(7);

        long ahora = System.currentTimeMillis();

        System.out.println("== LOGIN DE USUARIOS ==");
        cache.login("abc123", "Juan", "ADMIN", 5000);
        cache.login("xyz789", "Ana", "USER", -1000);
        cache.login("tok555", "Luis", "USER", 10000);

        cache.printCache();

        System.out.println("\n== VALIDANDO TOKENS ==");
        imprimirValidacion(cache.validate("abc123"));
        imprimirValidacion(cache.validate("xyz789"));
        imprimirValidacion(cache.validate("tok555"));

        System.out.println("\n== LOGOUT EXPLICITO DE abc123 ==");
        cache.logout("abc123");
        cache.printCache();

        System.out.println("\n== LIMPIEZA DE SESIONES EXPIRADAS ==");
        cache.cleanExpired();
        cache.printCache();

        System.out.println("\nSesiones activas restantes: " + cache.activeCount());
    }

    private static void imprimirValidacion(Session session) {
        if (session == null) {
            System.out.println("Sesion invalida o expirada.");
        } else {
            System.out.println("Sesion valida: " + session);
        }
    }
}

class Session {
    String token;
    String username;
    String role;
    long expiresAt;

    public Session(String token, String username, String role, long expiresAt) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.expiresAt = expiresAt;
    }

    public boolean estaExpirada() {
        return System.currentTimeMillis() > expiresAt;
    }

    @Override
    public String toString() {
        return username + " | role=" + role + " | token=" + token;
    }
}

class SessionCache {
    private NodoSesion[] tabla;
    private int size;

    public SessionCache(int size) {
        this.size = size;
        this.tabla = new NodoSesion[size];
    }

    private int hash(String token) {
        return (token.hashCode() & 0x7fffffff) % size;
    }

    public void login(String token, String username, String role, long ttlMs) {
        long expiresAt = System.currentTimeMillis() + ttlMs;
        Session session = new Session(token, username, role, expiresAt);

        int indice = hash(token);

        NodoSesion actual = tabla[indice];

        while (actual != null) {
            if (actual.session.token.equals(token)) {
                actual.session = session;
                System.out.println("Sesion actualizada en indice " + indice + ": " + username);
                return;
            }

            actual = actual.siguiente;
        }

        NodoSesion nuevo = new NodoSesion(session);
        nuevo.siguiente = tabla[indice];
        tabla[indice] = nuevo;

        System.out.println("Sesion registrada en indice " + indice + ": " + username);
    }

    public Session validate(String token) {
        int indice = hash(token);
        NodoSesion actual = tabla[indice];

        while (actual != null) {
            if (actual.session.token.equals(token)) {
                if (actual.session.estaExpirada()) {
                    logout(token);
                    return null;
                }

                return actual.session;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    public void logout(String token) {
        int indice = hash(token);
        NodoSesion actual = tabla[indice];
        NodoSesion anterior = null;

        while (actual != null) {
            if (actual.session.token.equals(token)) {
                if (anterior == null) {
                    tabla[indice] = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }

                System.out.println("Sesion eliminada: " + token);
                return;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        System.out.println("Token no encontrado: " + token);
    }

    public void cleanExpired() {
        int eliminadas = 0;

        for (int i = 0; i < tabla.length; i++) {
            NodoSesion actual = tabla[i];
            NodoSesion anterior = null;

            while (actual != null) {
                if (actual.session.estaExpirada()) {
                    eliminadas++;

                    if (anterior == null) {
                        tabla[i] = actual.siguiente;
                        actual = tabla[i];
                    } else {
                        anterior.siguiente = actual.siguiente;
                        actual = anterior.siguiente;
                    }
                } else {
                    anterior = actual;
                    actual = actual.siguiente;
                }
            }
        }

        System.out.println("Sesiones expiradas eliminadas: " + eliminadas);
    }

    public int activeCount() {
        int contador = 0;

        for (int i = 0; i < tabla.length; i++) {
            NodoSesion actual = tabla[i];

            while (actual != null) {
                contador++;
                actual = actual.siguiente;
            }
        }

        return contador;
    }

    public void printCache() {
        System.out.println("\n== ESTADO DEL CACHE ==");
        for (int i = 0; i < tabla.length; i++) {
            System.out.print("Indice " + i + ": ");

            NodoSesion actual = tabla[i];

            if (actual == null) {
                System.out.println("VACIO");
            } else {
                while (actual != null) {
                    System.out.print("[" + actual.session + "]");
                    if (actual.siguiente != null) {
                        System.out.print(" -> ");
                    }
                    actual = actual.siguiente;
                }
                System.out.println();
            }
        }
    }

    private static class NodoSesion {
        Session session;
        NodoSesion siguiente;

        NodoSesion(Session session) {
            this.session = session;
        }
    }
}
