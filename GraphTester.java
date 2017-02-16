public class GraphTester
{
	public static void main(String[] args)
	{
		UndirectedGraph test = new UndirectedGraph(7);
		test.addEdge(0, 2);
		test.addEdge(1, 2);
		test.addEdge(2, 3);
		test.addEdge(3, 5);
		test.addEdge(3, 1);
		test.addEdge(1, 5);
		test.addEdge(5, 4);
		test.addEdge(4, 6);
		System.out.println(test);
	}
}
