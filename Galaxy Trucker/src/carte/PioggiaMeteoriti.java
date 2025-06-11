package carte;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import componenti.Componente;
import componenti.Connettore;
import componenti.Lato;
import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;
import galaxyTrucker.Posizione;

public class PioggiaMeteoriti extends Carta{
	private final List<Meteorite> meteoriti;

	public PioggiaMeteoriti(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito, List<Meteorite> meteoriti) {
		super(id, NomeSpeciale.PIOGGIA_METEORITI , nome, livello, ggVolo, merce, equipaggio, credito);
		this.meteoriti=meteoriti;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Che prevede (in ordine di arrivo) " + meteoriti;
	}
	
	@Override
	//Da testare, logicamente dovrebbe funzionare
	void attivaCarta(Gioco flotta) {
	    List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
	    Scanner scanner = new Scanner(System.in);
	    Random rand = new Random();

	    for (Meteorite m : meteoriti) {
	        int tiro = (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);
	        System.out.println("Lancio dei dadi per meteorite " + m + ", risultato: " + tiro);

	        for (int i = 0; i < flotta.getNGiocatori(); i++) {
	            Nave nave = ordinate.get(i);

	            int scelta;
	            while (true) {
	                System.out.print("Giocatore " + nave.getColor() + ", vuoi difenderti con uno scudo? (0=No, 1=Si): ");
	                try {
	                    scelta = scanner.nextInt();
	                    scanner.nextLine();
	                    if (scelta == 0 || scelta == 1) break;
	                } catch (InputMismatchException e) {
	                    scanner.nextLine();
	                }
	                System.out.println("Input non valido! Inserisci 0 o 1.");
	            }

	            if (scelta == 1) {
	                if (nave.isLatoProtetto(m.getLato()) && m.getDimensione().equals(Dimensione.piccolo)) {
	                    if (nave.getEnergiaNave() >= 1) {
	                        nave.minusEnergiaNave(1);
	                        System.out.println("Giocatore " + nave.getColor() + ", difesa riuscita con lo scudo!");
	                    } else {
	                        System.out.println("Giocatore " + nave.getColor() + ", energia insufficiente per lo scudo.");
	                        if (nave.isLatoProtetto(m.getLato(), tiro)) {
	                            int sceltaC;
	                            while (true) {
	                                System.out.print("Che fortuna: avete il cannone a portata. Usarlo? (0=No, 1=Si): ");
	                                try {
	                                    sceltaC = scanner.nextInt();
	                                    scanner.nextLine();
	                                    if (sceltaC == 0 || sceltaC == 1) break;
	                                } catch (InputMismatchException ex) {
	                                    scanner.nextLine();
	                                }
	                                System.out.println("Input non valido! Inserisci 0 o 1.");
	                            }
	                            if (sceltaC == 1) {
	                                System.out.println("Ma sei serio? Senza energia non puoi neanche sparare!");
	                            }
	                        }
	                    }
	                } else if (nave.isLatoProtetto(m.getLato(), tiro) && m.getDimensione().equals(Dimensione.grande)) {
	                    int sceltaC;
	                    while (true) {
	                        System.out.print("Che fortuna: avete il cannone a portata. Usarlo? (0=No, 1=Si): ");
	                        try {
	                            sceltaC = scanner.nextInt();
	                            scanner.nextLine();
	                            if (sceltaC == 0 || sceltaC == 1) break;
	                        } catch (InputMismatchException ex) {
	                            scanner.nextLine();
	                        }
	                        System.out.println("Input non valido! Inserisci 0 o 1.");
	                    }
	                    if (sceltaC == 1) {
	                        if (nave.getEnergiaNave() >= 1) {
	                            nave.minusEnergiaNave(1);
	                            System.out.println("Giocatore " + nave.getColor() + ", difesa riuscita con il cannone!");
	                        } else {
	                            System.out.println("Giocatore " + nave.getColor() + ", mi dispiace, non hai energia per difenderti.");
	                        }
	                    }
	                }
	            } else {
	                if (nave.isLatoProtetto(m.getLato(), tiro)) {
	                    int sceltaC;
	                    while (true) {
	                        System.out.print("Che fortuna: avete il cannone a portata. Usarlo? (0=No, 1=Si): ");
	                        try {
	                            sceltaC = scanner.nextInt();
	                            scanner.nextLine();
	                            if (sceltaC == 0 || sceltaC == 1) break;
	                        } catch (InputMismatchException ex) {
	                            scanner.nextLine();
	                        }
	                        System.out.println("Input non valido! Inserisci 0 o 1.");
	                    }
	                    if (sceltaC == 1) {
	                        if (nave.getEnergiaNave() >= 1) {
	                            nave.minusEnergiaNave(1);
	                            System.out.println("Giocatore " + nave.getColor() + ", difesa riuscita con il cannone!");
	                        } else {
	                            System.out.println("Giocatore " + nave.getColor() + ", mi dispiace, non hai energia per difenderti.");
	                        }
	                    }
	                }
	            }

	            boolean fermato = false;
	            int nR = nave.getNRighe();
	            int nC = nave.getNColonne();
	            if (m.getLato().equals(Lato.up)) {
	                for (int r = 0; r < nR; r++) {
	                    Componente c = nave.getCella(new Posizione(r, tiro)).getComponente();
	                    if (c != null) {
	                        fermato = true;
	                        if (m.getDimensione().equals(Dimensione.piccolo)) {
	                            if (c.getConnettori(m.getLato()).equals(Connettore.assente))
	                                System.out.println("Meteorite piccolo rimbalza sul lato liscio.");
	                            else {
	                                nave.eliminaComponente(r, tiro);
	                                System.out.println("Meteorite piccolo distrugge componente in (" + r + "," + tiro + ").");
	                            }
	                        } else {
	                            System.out.println("Meteorite grande distrugge la nave di " + nave.getColor());
	                            nave.eliminaComponente(r, tiro);
	                        }
	                        break;
	                    }
	                }
	            } else if (m.getLato().equals(Lato.dw)) {
	                for (int r = nR - 1; r >= 0; r--) {
	                    Componente c = nave.getCella(new Posizione(r, tiro)).getComponente();
	                    if (c != null) {
	                        fermato = true;
	                        if (m.getDimensione().equals(Dimensione.piccolo)) {
	                            if (c.getConnettori(m.getLato()).equals(Connettore.assente))
	                                System.out.println("Meteorite piccolo rimbalza sul lato liscio.");
	                            else {
	                                nave.eliminaComponente(r, tiro);
	                                System.out.println("Meteorite piccolo distrugge componente in (" + r + "," + tiro + ").");
	                            }
	                        } else {
	                            System.out.println("Meteorite grande distrugge la nave di " + nave.getColor());
	                            nave.eliminaComponente(r, tiro);
	                        }
	                        break;
	                    }
	                }
	            } else if (m.getLato().equals(Lato.sx)) {
	                for (int c = 0; c < nC; c++) {
	                    Componente comp = nave.getCella(new Posizione(tiro, c)).getComponente();
	                    if (comp != null) {
	                        fermato = true;
	                        if (m.getDimensione().equals(Dimensione.piccolo)) {
	                            if (comp.getConnettori(m.getLato()).equals(Connettore.assente))
	                                System.out.println("Meteorite piccolo rimbalza sul lato liscio.");
	                            else {
	                                nave.eliminaComponente(tiro, c);
	                                System.out.println("Meteorite piccolo distrugge componente in (" + tiro + "," + c + ").");
	                            }
	                        } else {
	                            System.out.println("Meteorite grande distrugge la nave di " + nave.getColor());
	                            nave.eliminaComponente(tiro, c);
	                        }
	                        break;
	                    }
	                }
	            } else {
	                for (int c = nC - 1; c >= 0; c--) {
	                    Componente comp = nave.getCella(new Posizione(tiro, c)).getComponente();
	                    if (comp != null) {
	                        fermato = true;
	                        if (m.getDimensione().equals(Dimensione.piccolo)) {
	                            if (comp.getConnettori(m.getLato()).equals(Connettore.assente))
	                                System.out.println("Meteorite piccolo rimbalza sul lato liscio.");
	                            else {
	                                nave.eliminaComponente(tiro, c);
	                                System.out.println("Meteorite piccolo distrugge componente in (" + tiro + "," + c + ").");
	                            }
	                        } else {
	                            System.out.println("Meteorite grande distrugge la nave di " + nave.getColor());
	                            nave.eliminaComponente(tiro, c);
	                        }
	                        break;
	                    }
	                }
	            }
	            if (!fermato) {
	                System.out.println("Meteorite attraversa senza danni la nave di " + nave.getColor());
	            }
	        }
	    }

	    scanner.close();
	}

}