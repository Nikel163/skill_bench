package ru.skillbench.tasks.javaapi.collections;

import java.util.Iterator;
import java.util.Set;

/**
 * ЦЕЛИ ЗАДАЧИ<ul>
 * <li>Попрактиковаться в реализации типичных операций с древовидной структурой данных.</li>
 * <li>Научиться пользоваться одной из коллекций (на выбор).</li>
 * <li>Познакомиться с шаблонами проектирования Iterator и Chain of Responsibility.</li>
 * </ul>
 * <p/>
 * ЗАДАНИЕ<br/>
 * Реализовать класс объекта, представляющего собой узел некоторого дерева (например, UI-компонента "дерево").<br/>
 * <p/>
 * Пример похожей структуры данных: <a href="http://www.primefaces.org/docs/api/5.1/org/primefaces/model/DefaultTreeNode.html">
 *  DefaultTreeNode in JSF Primefaces</a><br/>
 * <p/>
 * РЕКОМЕНДАЦИЯ<br/>
 * Коллекцию дочерних узлов данного узла (children) рекомендуется инициализироваться лишь при необходимости, 
 *   т.е. при добавлении дочернего узла (шаблон "lazy initialization"). Это значит, что связанные с children getter-методы 
 *   (включая {@link #isLeaf()}) не должны вызывать метод, создающий при необходимости пустую коллекцию.<br/>
 * <p/>
 * ПРИМЕЧАНИЕ<br/>
 * Нежелательно переопределять метод {@link #equals(Object)} в вашем классе: это может привести к ошибкам проверки.<br/>
 * 
 * @author Georgii Kolpakov
 * @author Alexey Evdokimov
 */
public interface TreeNode {
    
    /**
     * Возвращает родительский объект <code>TreeNode</code>.
     */
    TreeNode getParent();
    /**
     * Задает родительский объект <code>TreeNode</code>.<br/>
     * Обычно вызывается в методах {@link #addChild(TreeNode)} и {@link #removeChild(TreeNode)}
     *  родительского объекта <code>TreeNode</code>.
     */
    void setParent(TreeNode parent);
    /**
     * Возвращает корень дерева, содержащего данный объект <code>TreeNode</code>.
     * @return корневой узел. Или <code>null</code>, если у данного узла нет родителя.
     */
    TreeNode getRoot();

    /**
     * Возвращает <code>false</code>, если <code>TreeNode</code> имеет ненулевое число дочерних узлов.
     * @return <code>true</code>, если данный узел является листовым (т.е. не имеет дочерних узлов)
     */
    boolean isLeaf();
    /**
     * Возвращает число дочерних узлов данного <code>TreeNode</code>.
     */
    int getChildCount();
    /**
     * Возвращает дочерние узлы данного <code>TreeNode</code> в виде {@link Iterator}.
     */
    Iterator<TreeNode> getChildrenIterator();
    /**
     * Добавляет указанный в аргументе <code>TreeNode</code> в качестве дочернего узла
     *  и задает ему в качестве родителя <code>this</code>.
     */
    void addChild(TreeNode child);
    /**
     * Удаляет указанный в аргументе <code>TreeNode</code> из коллекции дочерних узлов и (если успешно)
     *  задает ему в качестве родителя <code>null</code> (чтобы обеспечить согласованное состояние дерева).<br/>
     * Узел ищется в коллекции и удаляется из нее по правилам любой коллекции: <br/>
     * а) поиск/удаление успешно, если в ней нашелся объект, равный указанному в смысле метода {@link Object#equals(Object)};<br/>
     * б) если в коллекции несколько таких объектов (если это не {@link Set}), то удаляется только один.
     * @return <code>true</code> - если удаление успешно, <code>false</code> - если такой дочерний узел не найден.
     */
    boolean removeChild(TreeNode child);
    
    /**
     * Возвращает признак "развернутости / свернутости" данного <code>TreeNode</code>
     *  (в UI-компонентах типа "дерево" от этого зависит иконка и показ дочерних узлов).<br/>
     * Узел "свернут" по умолчанию - то есть, если {@link #setExpanded(boolean)} не вызывался.
     * @return <code>true</code> - если узел развернут, <code>false</code></code></code> - если узел "свернут" (collapsed)

     */
    boolean isExpanded();
    /**
     * Задает признак "развернутости" (expanded) данному <code>TreeNode</code> и рекурсивно всем его дочерним узлам
     * @param expanded <code>true</code> - разворачивает эту ветвь дерева, <code>false</code> - сворачивает ее.
     */
    void setExpanded(boolean expanded);
    
    /**
     * @return "пользовательский" (заданный извне) объект данных, хранящийся в этом <code>TreeNode</code>.
     *  Или <code>null</code>, если {@link #setData(Object)} не вызывался.
     */
    Object getData();
    /**
     * Задает "пользовательский" объект данных для хранения в этом <code>TreeNode</code>.
     */
    void setData(Object data);
    /**
     * Возвращает строковое представление пути от корня дерева до данного <code>TreeNode</code>.<br/>
     * Элементы пути разделяются символами "->".<br/>
     * Каждый элемент пути - это либо getData().toString(), либо строка "empty", если getData()==null.<br/>
     * Например: "rootNode0->node1->node13->empty" ("rootNode0" - это в данном примере результат вызова метода
     *  getRoot().getData().toString() ).
     */
    String getTreePath();
    /**
     * Среди цепочки родительских узлов данного <code>TreeNode</code> метод находит (первый) узел с заданным объектом <code>data</code>.<br/>
     * По соглашению, "цепочка родительских узлов" содержит сам данный узел (то есть, возможно следующее: obj.findParent(*) == obj).<br/>
     * Объекты <code>data</code> должны сравниваться с помощью {@link Object#equals(Object)}, а если <code>data == null</code>,
     *  тогда должен возвращаться родительский узел, у которого <code>getData() == null</code>).
     * @param data Объект поиска; может быть равен <code>null</code>
     * @return Найденный узел. Или <code>null</code> если не было найдено узла, содержащего такой <code>data</code>.
     */
    TreeNode findParent(Object data);
    /**
     * Среди дочерних узлов данного <code>TreeNode</code> метод находит (первый) узел с заданным объектом <code>data</code>.<br/>
     * Причем ищет такой узел рекурсивно: если некоторый дочерний узел не имеет заданного объекта <code>data</code>, 
     *  он ищется среди детей этого дочернего узла, и так далее.<br/>
     * Объекты <code>data</code> должны сравниваться с помощью {@link Object#equals(Object)}, а если <code>data == null</code>,
     *  тогда должен возвращаться дочерний узел, у которого <code>getData() == null</code>).
     * @param data Объект поиска; может быть равен <code>null</code>
     * @return Найденный узел. Или <code>null</code> если не было найдено узла, содержащего такой <code>data</code>.
     */
    TreeNode findChild(Object data);
}
